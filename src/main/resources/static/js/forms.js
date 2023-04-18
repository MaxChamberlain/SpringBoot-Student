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
        let token = text?.split('=')[1]?.replace(')', '') ?? null;
        if (token != null) {
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