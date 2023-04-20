async function updateAccountDetails(){
    let allowTexts = document.getElementById('texts-opted').checked
    let allowEmails = document.getElementById('emails-opted').checked

    let id = window.location.href.split('/')[window.location.href.split('/').length - 1]

    let data = {
        id,
        allowTexts,
        allowEmails
    }

    await fetch('/api/v1/account/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(() => window.location.reload())
}