package com.bomiyr.alertdialogfragment.example

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bomiyr.alertdialogfragment.AlertDialogFragment
import com.bomiyr.alertdialogfragment.AlertDialogResult
import kotlinx.android.synthetic.main.fragment_example.*

private const val DIALOG_REQUEST_CODE = 10
private const val LOG_TAG = "ExampleFragment"

class ExampleFragment : Fragment, AlertDialogFragment.AlertDialogFragmentListener {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_example, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            AlertDialogFragment
                .Builder()
                .setTitle(R.string.example_title)
                .setMessage("Example message")
                .setPositiveButton(android.R.string.yes)
                .setNegativeButton(android.R.string.no)
                .setNeutralButton("Maybe")
                .create(DIALOG_REQUEST_CODE)
                .show(childFragmentManager, "Dialog")
        }
    }

    override fun onAlertDialogFragmentResult(requestCode: Int, result: AlertDialogResult) {
        if (requestCode == DIALOG_REQUEST_CODE) {
            Log.i(LOG_TAG, "AlertDialogFragment result: ${result.name}")
        }
    }
}