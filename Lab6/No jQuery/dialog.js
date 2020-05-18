const openDialogButton = document.querySelectorAll('[dialog-open-button]');
const closeDialogButton = document.querySelectorAll('[dialog-close-button]');
/* const overlay = document.querySelectorAll('.overlay'); */

function openDialog(dialog) {
	if (dialog == null)
		return;
	dialog.classList.add('active');
	//overlay.classList.add('active');
}


function closeDialog(dialog) {
	if (dialog == null)
		return;
	dialog.classList.remove('active');
	//overlay.classList.remove('active');
}


closeDialogButton.forEach(button => {
	button.addEventListener('click', () => {
		const dialog = closeDialogButton.closest('.dialog'); //Get the closest parent of class .dialog
		closeDialog(dialog);
	})
})

openDialogButton.forEach(button => {
	button.addEventListener('click', () => {
		const dialog = document.querySelector(button.dataset.openButton)
		openDialog(dialog);
	})
})

// Close the dialog when clicking on the overlay
/*overlay.addEventListener('click', () => {
	const dialogs = document.querySelectorAll('.dialog.active');
	dialogs.forEach(dialog => {
		closeDialog(dialog);
	})
}) */