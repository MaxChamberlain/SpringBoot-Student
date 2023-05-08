async function onLogin() {
    let email = document.getElementById('email')?.value ?? '';
    let password = document.getElementById('password')?.value ?? '';

    console.log("Username: " + email);
    console.log("Password: " + password);

    if (email == null || email == "") {
        alert("Please enter your email!");
        return false;
    } else if (password == null || password == "") {
        alert("Please enter your password!");
        return false;
    }
    try {
        let data = await fetch(
            '/api/v1/auth/authenticate',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email,
                    password
                })
            }
        )
        let text = await data.text()
        console.log(text)
        let token = text?.split('=')[1]?.replace(')', '') ?? null;
        if (token != null) {
            if (text.includes("stylesheet")) return
            document.cookie = "ACCESS_TOKEN=Bearer " + token;
        } else {
            document.cookie = "ACCESS_TOKEN=; max-age=0";
        }
        window.location.href='/'
    } catch (e) {
        console.log(e)
        document.cookie = "ACCESS_TOKEN=; max-age=0";
        window.location.href = "/login"
    }
}

async function checkMfa(){
    let code = document.getElementById('code')?.value ?? '';
    let email = document.getElementById('email')?.value ?? '';
    try{
        let data = await fetch(
        `/mfa/checkCode/${email}/${code}`,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        let status = await data.status
        if(status === 200){
            await onLogin();
        } else {
            alert("Invalid code!")
        }
    } catch (e) {
        console.log(e)
        window.location.reload()
    }
}

async function onInitialLogin(){
    let email = document.getElementById('email')?.value ?? '';
    try{
        let data = await fetch(
        `/mfa/getCode/${email}`,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        console.log(await data.text())
        document.getElementById('enter-2fa-code').style.display = 'block'
    } catch (e) {
        console.log(e)
        window.location.reload()
    }
}

function onOpenLogin(){
    document.cookie = 'ACCESS_TOKEN=; max-age=0';
}

function onOpenRegister(){
    document.cookie = 'ACCESS_TOKEN=; max-age=0';
}

async function onRegister(){
    let firstname = document.getElementById('firstname')?.value ?? ''
    let lastname = document.getElementById('lastname')?.value ?? ''
    let email = document.getElementById('email')?.value ?? ''
    let password = document.getElementById('password')?.value ?? ''
    let phone = document.getElementById('phone')?.value ?? ''

    try {
        let data = await fetch(
            '/api/v1/auth/register',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    firstname,
                    lastname,
                    email,
                    password,
                    phone
                })
            }
        )
        console.log(data)
        let text = await data.text()
        let token = text?.split('=')[1]?.replace(')', '') ?? null;
        if (token != null) {
            if (text.includes("stylesheet")) return
            document.cookie = "ACCESS_TOKEN=Bearer " + token;
        } else {
            document.cookie = "ACCESS_TOKEN=; max-age=0";
        }
        window.location.href='/'
    } catch (e) {
        console.log(e)
        document.cookie = "ACCESS_TOKEN=; max-age=0";
        window.location.href = "/login"
    }
}