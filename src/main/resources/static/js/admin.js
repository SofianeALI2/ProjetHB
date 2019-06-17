
function verifierLogin(login) {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierLogin/" + login;
    return new Promise(function (resolve, reject) {
        httpRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status !== 200) {
                reject('error serrver');
            }
            if (this.readyState == 4 && this.status == 200) {
                try {
                    var respJSON = JSON.parse(this.responseText);
                    console.log("login");
                    console.log(respJSON);
                } catch (e) {
                    console.log(e);
                    reject("invalide Json");
                }
                resolve(respJSON.login === login);
            }
        };
        httpRequest.open("GET", url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
        httpRequest.send();
    });

}

function verifierPassword(password,login) {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierPassword/" +password+"/"+login;
    return new Promise(function (resolve, reject) {
        httpRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status !== 200) {
                reject('error serrver');
            }
            if (this.readyState == 4 && this.status == 200) {
                try {
                    var respJSON = JSON.parse(this.responseText);
                    console.log("password");
                    console.log(respJSON);
                } catch (e) {
                    console.log(e);
                    reject("invalide Json");
                }
                resolve(respJSON.password === password);
            }
        };
        httpRequest.open("GET", url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
        httpRequest.send();
    });

}
function verifierCompte() {
    var login = document.getElementById("login");
    var email = document.getElementById("email");
    var forme = document.getElementById("forme");

    console.log(verifierLogin(login.value));
    console.log(verifieremail(email.value));

    verifieremail(email.value).then(function (notUniqueEmail) {
        console.log("verifieremail result");
        console.log("notUniqueEmail", notUniqueEmail)
        if (notUniqueEmail === true) {
            alert('Email deja existant')
        } else {
            verifierLogin(login.value).then(function onLoginResult(login) {
                    if (login === false) {
                        forme.submit();
                    } else {
                        alert('login incorrect');
                    }
                }
            )
        }
    }).catch(function onError(error) {
        console.log(error)
    });


}
