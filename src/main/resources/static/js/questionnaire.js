function confirmIdentity(){
    let fieldToHide = document.getElementById('confirmation')
    let fieldToShow = document.getElementById('questions')
    let submitButton = document.getElementById('submit-button')

    fieldToHide.style.display = 'none'
    fieldToShow.style.display = 'block'
    submitButton.style.display = 'block'
}