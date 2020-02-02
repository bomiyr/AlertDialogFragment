# AlertDialogFragment
AlertDialog.Builder API, but using DialogFragment

# Supported features

Available Builder functions
- set cancelable flag
- set message with resourceId or CharSequence
- set title with resourceId or CharSequence
- set positive/negative/neutral buttons with resourceId or CharSequence

Your calling Activity or Fragment should implement AlertDialogFragmentListener interface to get result.

Be careful choosing FramgentManager in case of using from Fragment:
- If you create AlertDialogFragment with Builder.create(requestCode: Int) use Fragment.childFragmentManager
- If you create AlertDialogFragment with Builder.create(requestCode: Int, targetFragment: Fragment) use Fragment.fragmentManager

# Example


    class ExampleFragment : Fragment, AlertDialogFragment.AlertDialogFragmentListener {
       ...
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


# Integration

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency

	dependencies {
	        implementation 'com.github.bomiyr:AlertDialogFragment:v1.0'
	}

