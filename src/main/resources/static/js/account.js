async function updateAccountDetails(){
    let allowTexts = document.getElementById('texts-opted').checked
    let allowEmails = document.getElementById('emails-opted').checked
    let phone = document.getElementById('phone').value
    let email = document.getElementById('email').value

    let id = window.location.href.split('/')[window.location.href.split('/').length - 1]

    let data = {
        id,
        allowTexts,
        allowEmails,
        phone,
        email,
    }

    if(!phone || !email) return;
    if(allowTexts === null) allowTexts = false;
    if(allowEmails === null) allowEmails = false;

    let response = await fetch('/api/v1/account/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    let text = await response.text()
    let token = text?.split('=')[1]?.replace(')', '') ?? null;
    if (token != null) {
        if (text.includes("stylesheet")) return
        document.cookie = "ACCESS_TOKEN=Bearer " + token;
    } else {
        document.cookie = "ACCESS_TOKEN=; max-age=0";
    }
    window.location.reload()
}