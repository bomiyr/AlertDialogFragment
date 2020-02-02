package com.bomiyr.alertdialogfragment

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class AlertDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_BUILDER = "ARG_BUILDER"

        const val ACTION_POSITIVE_BUTTON_CLICKED =
            "AlertDialogFragment_ACTION_POSITIVE_BUTTON_CLICKED"
        const val ACTION_NEGATIVE_BUTTON_CLICKED =
            "AlertDialogFragment_ACTION_NEGATIVE_BUTTON_CLICKED"
        const val ACTION_NEUTRAL_BUTTON_CLICKED =
            "AlertDialogFragment_ACTION_NEUTRAL_BUTTON_CLICKED"
        const val ACTION_DISMISSED = "AlertDialogFragment_ACTION_DISMISSED"
    }

    private lateinit var builder: Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        builder = arguments?.getParcelable(ARG_BUILDER)!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())

        builder.messageResId?.let {
            dialogBuilder.setMessage(it)
        }
        builder.message?.let {
            dialogBuilder.setMessage(it)
        }

        builder.titleResId?.let {
            dialogBuilder.setTitle(it)
        }
        builder.title?.let {
            dialogBuilder.setTitle(it)
        }

        builder.positiveButtonResId?.let {
            dialogBuilder.setPositiveButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_POSITIVE_BUTTON_CLICKED)
                )
            }
        }
        builder.positiveButton?.let {
            dialogBuilder.setPositiveButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_POSITIVE_BUTTON_CLICKED)
                )
            }
        }

        builder.negativeButtonResId?.let {
            dialogBuilder.setNegativeButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_NEGATIVE_BUTTON_CLICKED)
                )
            }
        }
        builder.negativeButton?.let {
            dialogBuilder.setNegativeButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_NEGATIVE_BUTTON_CLICKED)
                )
            }
        }
        builder.neutralButtonResId?.let {
            dialogBuilder.setNeutralButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_NEUTRAL_BUTTON_CLICKED)
                )
            }
        }
        builder.neutralButton?.let {
            dialogBuilder.setNeutralButton(it) { _, _ ->
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    Intent(ACTION_NEUTRAL_BUTTON_CLICKED)
                )
            }
        }

        isCancelable = builder.cancelable

        return dialogBuilder
            .create()
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        targetFragment?.onActivityResult(
            targetRequestCode,
            Activity.RESULT_OK,
            Intent(ACTION_DISMISSED)
        )
    }

    class Builder() : Parcelable {
        var cancelable: Boolean = true

        var message: CharSequence? = null
        var messageResId: Int? = null

        var title: CharSequence? = null
        var titleResId: Int? = null

        var positiveButtonResId: Int? = null
        var positiveButton: CharSequence? = null

        var negativeButtonResId: Int? = null
        var negativeButton: CharSequence? = null

        var neutralButtonResId: Int? = null
        var neutralButton: CharSequence? = null

        constructor(parcel: Parcel) : this() {
            cancelable = parcel.readByte() != 0.toByte()
            message = parcel.readString()
            messageResId = parcel.readValue(Int::class.java.classLoader) as? Int
            title = parcel.readString()
            titleResId = parcel.readValue(Int::class.java.classLoader) as? Int
            positiveButtonResId = parcel.readValue(Int::class.java.classLoader) as? Int
            positiveButton = parcel.readString()
            negativeButtonResId = parcel.readValue(Int::class.java.classLoader) as? Int
            negativeButton = parcel.readString()
            neutralButtonResId = parcel.readValue(Int::class.java.classLoader) as? Int
            neutralButton = parcel.readString()
        }

        fun setCancelable(value: Boolean): Builder {
            cancelable = value
            return this
        }

        fun setMessage(value: CharSequence?): Builder {
            message = value
            return this
        }

        fun setMessage(@StringRes value: Int?): Builder {
            messageResId = value
            return this
        }

        fun setTitle(value: CharSequence?): Builder {
            title = value
            return this
        }

        fun setTitle(@StringRes value: Int?): Builder {
            titleResId = value
            return this
        }

        fun setPositiveButton(value: CharSequence?): Builder {
            positiveButton = value
            return this
        }

        fun setPositiveButton(@StringRes value: Int?): Builder {
            positiveButtonResId = value
            return this
        }

        fun setNegativeButton(value: CharSequence?): Builder {
            negativeButton = value
            return this
        }

        fun setNegativeButton(@StringRes value: Int?): Builder {
            negativeButtonResId = value
            return this
        }


        fun setNeutralButton(value: CharSequence?): Builder {
            neutralButton = value
            return this
        }

        fun setNeutralButton(@StringRes value: Int?): Builder {
            neutralButtonResId = value
            return this
        }

        fun create(targetFragment: Fragment, requestCode: Int): AlertDialogFragment {
            return AlertDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_BUILDER, this@Builder)
                }
                setTargetFragment(targetFragment, requestCode)
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeByte(if (cancelable) 1 else 0)
            parcel.writeString(message?.toString())
            parcel.writeValue(messageResId)
            parcel.writeString(title?.toString())
            parcel.writeValue(titleResId)
            parcel.writeValue(positiveButtonResId)
            parcel.writeString(positiveButton?.toString())
            parcel.writeValue(negativeButtonResId)
            parcel.writeString(negativeButton?.toString())
            parcel.writeValue(neutralButtonResId)
            parcel.writeString(neutralButton?.toString())
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Builder> {
            override fun createFromParcel(parcel: Parcel): Builder {
                return Builder(parcel)
            }

            override fun newArray(size: Int): Array<Builder?> {
                return arrayOfNulls(size)
            }
        }
    }
}