package com.example.whattosee.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.whattosee.databinding.ContactFragmentBinding
import com.example.whattosee.model.Contact
import com.example.whattosee.view.adapters.RVContactAdapter


class ContactFragment : BaseFragment() {
    private var _binding: ContactFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { RVContactAdapter(requireContext()) }

    companion object {

        const val REQUEST_CODE = 42

        fun getInstance(): ContactFragment {
            return ContactFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setAdapter() = with(binding) {
        rvContacts.adapter = adapter
        adapter.onClickListener = {
            it.phoneNumber?.let {
                callTo(it)
            }
        }
    }

    fun setAdapterList(list: List<Contact>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
    }

    private fun callTo(phoneNumber: String) {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED -> {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$it"))
                    startActivity(intent)
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Разрешение совершать звонки")
                        .setMessage("Объснение")
                        .setPositiveButton("Предоставить доступ") { _, _ ->
                            requestPermission()
                        }
                        .setNegativeButton("Не надо") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("Доступ к контактам")
                            .setMessage("Объяснения")
                            .setNegativeButton("Закрыть") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                    }
                }
                return
            }
        }
    }

    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к контактам")
                        .setMessage("Объснение")
                        .setPositiveButton("Предоставить доступ") { _, _ ->
                            requestPermission()
                        }
                        .setNegativeButton("Не надо") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE
            ), REQUEST_CODE
        )
    }

    private fun getContacts() {
        context?.let {
            val contentResolver = it.contentResolver
            val cursorWithContacts = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            val listContacts: MutableList<Contact> = mutableListOf()
            cursorWithContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val id =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val hasPhoneNumber =
                            cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        var phoneNumber: String? = null
                        if (hasPhoneNumber > 0) {
                            val cursorPhone = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                arrayOf(id),
                                null
                            )
                            cursorPhone?.let {
                                if (it.moveToNext()) {
                                    phoneNumber =
                                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                }
                            }

                        }
                        val contact = Contact(id, name, phoneNumber)
                        listContacts.add(contact)
                    }
                }
            }
            cursorWithContacts?.close()
            setAdapterList(listContacts)
        }
    }


}