function confirmIdentity(){
    let fieldToHide = document.getElementById('confirmation')
    let fieldToShow = document.getElementById('questions')
    let submitButton = document.getElementById('submit-button')

    fieldToHide.style.display = 'none'
    fieldToShow.style.display = 'block'
    submitButton.style.display = 'block'
}

const submitQuestionnaire = async (questions) => {
    console.log(questions)
    const userJWT = getCookie('ACCESS_TOKEN')
    const type = questions.question1
    const title = questions.question5
    const description = "This is a description"

    const response = await fetch('/api/v1/activity/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userJWT, type, title, description })
    })
    console.log(await response.text())
}