function addloyer() {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierSession";

    httpRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');

            if (data.login!="null") {

                var btn = document.getElementById('ok');

                if (btn != null) {
                    btn.style.visibility = "hidden";
                }
                var html =
                    '<div class="big-model mx-auto  container-fluid  " id="connexion" required>\n' +
                    '<div class="model-content-decon mx-auto container-fluid" id ="layerins" required>\n' +
                    '    <form action="/deconnexion" method="get" class="reglageForme">\n' +
                    '<br><br><br><p >  ' + data.nomPrenom + ' </p>' +
                    '<p > login: ' + data.login + '  </p>' +
                    '<p > email: ' + data.email + '  </p>' +
                    '<button type="submit" class="btn btn-success"> deconnexion </button>' +
                    '</form><br><br>' +
                    '</div>\n' +
                    '</div>\n';
                $("body").append(html);
            } else {
                //var main = document.getElementById('main');
                var btn = document.getElementById('ok').style.visibility = "hidden";
                var html = '<div class="big-model mx-auto  container-fluid  " id="connexion" required>\n' +
                    '<div class="model-content-con mx-auto container-fluid" id ="layerins" required>\n' +
                    '    <h2> Nom de Site </h2>\n' +
                    '    <form action="/connexion" method="post" class="reglageForme">\n' +
                    '    <p> Login </p> <input type="text" name="login" placeholder="login" class="form-control" >\n' +
                    '    <p> Password </p> <input type="password" name="password" placeholder="password" class="form-control"><br><br>\n' +

                    '        <button type="submit" class="btn btn-success" >\n' +
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

        }
    };
    httpRequest.open("GET", url, true);
    httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
    httpRequest.send();
}

function verifierSession() {
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/verifierSession";
    var session= document.getElementById('session');
    httpRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');
            var dd=data.login;
            console.log(dd);
            if (data.login != "null") {
            session.innerHTML=data.login;
            } else {
                session.innerHTML="not connected  ";
            }
            }
            };
            httpRequest.open("GET", url, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
            httpRequest.send();

            }


function addLoyerInscription() {
    //var main = document.getElementById('main');
  //  var btn=document.getElementById('ok').style.visibility="hidden";
    var con=document.getElementById("connexion");
    con.style.visibility="hidden";
    var html='<div class="big-model mx-auto container-fluid">\n' +
        '<div class="model-content-insc mx-auto container-fluid" id ="layerins">\n' +
        '<form action="/utilisateur" method="get" name="formAddUser" id="formAddUser">\n'+
        '    <h2> Add user </h2>\n' +
        '    <p> Name </p> <input type="text" name="name" placeholder="name" class="form-control"  required >\n' +
        '    <p> Forname </p> <input type="text" name="forename" placeholder="forename" class="form-control"  required>\n' +
        '    <p> Login </p> <input type="text" name="login" placeholder="login" class="form-control"  required>\n' +
        '    <p> Password </p> <input type="password" name="password" placeholder="password" class="form-control" required>\n' +
        '    <p> Email</p> <input type="email" name="email" placeholder="email" class="form-control"  required>\n' +
        '        <button type="submit" class="btn btn-success">\n' +
        '     <span>\n' +
        '     <i class="fas fa-user-plus"></i>\n' +
        '     </span>\n' +
        '        </button></form>\n' +
        '</div>\n' +
        '</div>\n';
    $("body").append(html);
}