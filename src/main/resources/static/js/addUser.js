function addloyer() {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierSession";

    httpRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');
            if (data.login != "null") {
                loyerDeconexion(data.nomPrenom, data.login, data.email)
            } else {

                loyerConnexion();
            }
        }
    };
    httpRequest.open("GET", url, true);
    httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
    httpRequest.send();
}

function loyerDeconexion(nomPrenom, login, email) {
    var btn = document.getElementById('ok');
    if (btn != null) {
        btn.style.visibility = "hidden";
    }
    var html =
        '<div class="big-model mx-auto  container-fluid  " id="connexion" >\n' +
        '<div class="model-content-decon mx-auto container-fluid col-12" id ="layerins" >\n' +
        '    <form action="/deconnexion" method="get" class="reglageForme">\n' +
        '<br><br><br><p >  ' + nomPrenom + ' </p>' +
        '<p > login: ' + login + '  </p>' +
        '<p > email: ' + email + '  </p>' +
        '<button type="submit" class="btn btn-success"> deconnexion </button>' +
        '</form><br><br>' +
        '</div>\n' +
        '</div>\n';
    $("body").append(html);
}

function loyerConnexion() {
    var btn = document.getElementById('ok');
    if (btn != null) {
        btn.style.visibility = "hidden";
    }
    var html = '<div class="big-model mx-auto  container-fluid  " id="connexion" >\n' +
        '<div class="model-content-con mx-auto container-fluid col-12" id ="layerins" >\n' +
        ' <div class="container-fluid"> ' +
        '<form action="/conadmin" method="get">' +
        '<button type="submit" class="btn btn-success">Admin </button></form>' +
        '  <h2> World Countries Data </h2>\n' +
        '</div>  ' +
        '  <form action="/connexion" method="post" class="reglageForme" id="forme">\n' +
        '    <p> Login </p> <input type="text" name="login" placeholder="login" class="form-control" id="login" required >\n' +
        '    <p> Password </p> <input type="password" name="password" ' +
        'placeholder="password" class="form-control" id="password" required><br><br>\n' +
        '        <button type="button" class="btn btn-success" onclick="verifierUserConection()" >\n' +
        '     <span>\n' +
        '     <i class="fas fa-check-circle"></i>\n' +
        '     </span>\n' +
        '        </button></form><br><br>\n' +
        '        <button type="submit" class="btn btn-success" onclick="addLoyerInscription()" >\n' +
        '     Inscription\n' +
        '        </button>\n' +

        '</div>\n' +
        '</div>\n';
    $("body").append(html);
}

function addLoyerInscription() {
    //var main = document.getElementById('main');
    //  var btn=document.getElementById('ok').style.visibility="hidden";
    var con = document.getElementById("connexion");
    con.style.visibility = "hidden";
    var html = '<div class="big-model mx-auto container-fluid ">\n' +
        '<div class="model-content-insc mx-auto container-fluid col-12" id ="layerins" >\n' +
        '<form action="/utilisateur" method="get" name="formAddUser" id="forme">\n' +
        '    <h2> Add user </h2>\n' +
        '    <p> Name </p> <input type="text" name="name" placeholder="name" class="form-control"  required >\n' +
        '    <p> Forname </p> <input type="text" name="forename" placeholder="forename" class="form-control"  required>\n' +
        '    <p> Login </p> <input type="text" name="login" placeholder="login" class="form-control"  id="login" required>\n' +
        '    <p> Password </p> <input type="password" name="password" placeholder="password" class="form-control" required>\n' +
        '    <p> Email</p> <input type="email" name="email" placeholder="email" class="form-control" id="email" required>\n' +
        '        <button type="button" class="btn btn-success" onclick="verifierCompte()">\n' +
        '     <span>\n' +
        '     <i class="fas fa-user-plus"></i>\n' +
        '     </span>\n' +
        '        </button></form>\n' +
        '</div>\n' +
        '</div>\n';
    $("body").append(html);
}


function verifierSession() {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierSession";
    var session = document.getElementById('session');
    httpRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');
            var dd = data.login;
            console.log(dd);
            if (data.login != "null") {
                session.innerHTML = data.login;
            } else {
                session.innerHTML = "not connected  ";
            }
        }
    };
    httpRequest.open("GET", url, true);
    httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
    httpRequest.send();
}

var compemail;

function verifieremail(email) {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierEmail/" + email;
    return new Promise(function (resolve, reject) {
        httpRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status !== 200) {
                reject('error serrver');
            }
            if (this.readyState == 4 && this.status == 200) {
                console.log("la");
                console.log(this.responseText);
                try {
                    var respJSON = JSON.parse(this.responseText);
                    console.log(respJSON);
                    console.log(respJSON.email);
                } catch (e) {
                    console.log(e);
                }
                resolve(respJSON.email === email);

            }
        };
        httpRequest.open("GET", url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
        httpRequest.send();
    });


}

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


function verifierUserConection() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var forme=document.getElementById("forme");

    console.log(verifierLogin(login.value));
    console.log(verifierPassword(password.value));

    verifierLogin(login.value).then(function (veriflogin) {
        console.log("verifieremail result");
        console.log("veriflogin", veriflogin)
        if (veriflogin === false) {
            alert('Email incorrect')
        } else {
            verifierPassword(password.value,login.value).then(function onLoginResult(verifpassword) {
                    if (verifpassword === false) {
                        alert('Password incorrect');
                    } else {
                        forme.submit();
                    }
                }
            )
        }
    }).catch(function onError(error) {
        console.log(error)
    });


}
