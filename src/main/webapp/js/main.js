/**
 * author jordi
 */

var url = "http://localhost:8080/practica11";

if (sessionStorage.getItem("username") != null) {
    onload = cargarPrestamos();
}

function checkUser() {
    if (sessionStorage.getItem("username") == null) {
        document.getElementById("login").style.visibility = "visible";
        document.getElementById("register").style.visibility = "visible";
        document.getElementById("logout").style.visibility = "hidden";
        document.getElementById("hipotecas").style.visibility = "hidden";
        document.getElementById("persona").innerHTML = "Invitado";
    } else {
        document.getElementById("login").style.visibility = "hidden";
        document.getElementById("register").style.visibility = "hidden";
        document.getElementById("logout").style.visibility = "visible";
        document.getElementById("hipotecas").style.visibility = "visible";
        document.getElementById("persona").innerHTML = sessionStorage.getItem("username");
    }
}

// Esta función se encarga de enviar los datos para calcular la mensualidad de la hipoteca
function generarCalculo() {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            document.getElementById("print").innerHTML = http.responseText;
            if (sessionStorage.getItem("username") != null) {
                document.getElementById("print").innerHTML += "<br><button class='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' onclick='guardarPrestamo()'>Guardar</button>";
            }
        }
    }
    http.open("POST", "Calcular", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("interes=" + document.getElementById("interes").value +
        "&&capital=" + document.getElementById("capital").value +
        "&&meses=" + document.getElementById("meses").value);

}

// Esta función se encarga de enviar los datos para calcular la tabla de amortización de la hipoteca
function generarTabla() {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            document.getElementById("print").innerHTML = http.responseText;
            if (sessionStorage.getItem("username") != null) {
                document.getElementById("print").innerHTML += "<br><button onclick='guardarPrestamo()'>Guardar</button>";
            }
        }
    }
    http.open("POST", "CalculoTabla", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("interes=" + document.getElementById("interes").value +
        "&&capital=" + document.getElementById("capital").value +
        "&&meses=" + document.getElementById("meses").value);
}

// Esta función se encarga de enviar los datos de registro del usuario
function registrar() {
    if (document.getElementById("password").value != document.getElementById("password2").value) {
        alert("Las contraseñas no coinciden");
        return;
    } else {
        let http = new XMLHttpRequest;
        http.onreadystatechange = function() {
            if (http.readyState == 4 && http.status == 200) {
                alert(http.responseText);
            }
        }
        http.open("POST", "Registrar", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("username=" + document.getElementById("username").value +
            "&&password=" + document.getElementById("password").value);
    }
}

// Esta función se encarga de enviar los datos de login del usuario
function login() {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            if (http.responseText == document.getElementById("username").value) {
                alert("Bienvenido " + http.responseText);
                sessionStorage.setItem("username", document.getElementById("username").value);
                document.getElementById("persona").innerHTML = sessionStorage.getItem("username");
                window.location.href = url;
            } else {
                alert("Usuario o contraseña incorrectos");
            }
        }
    }
    http.open("POST", "Login", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("username=" + document.getElementById("username").value +
        "&&password=" + document.getElementById("password").value);
}

function logout() {
    sessionStorage.removeItem("username");
    // Recargamos la pagina
    location.reload();
}

function guardarPrestamo() {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            alert("Presupuesto guardado");
        }
    }
    http.open("POST", "GuardarPrestamo", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("interes=" + document.getElementById("interes").value +
        "&&capital=" + document.getElementById("capital").value +
        "&&meses=" + document.getElementById("meses").value +
        "&&username=" + sessionStorage.getItem("username"));
}

function cargarPrestamos() {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            document.getElementById("cargarHipotecas").innerHTML = http.responseText;
        }
    }
    http.open("POST", "CargarPrestamos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("username=" + sessionStorage.getItem("username"));
}

function cargarPresupuestoID(numero) {
    let http = new XMLHttpRequest;
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            document.getElementById("imprimirHipoteca").innerHTML = http.responseText;
        }
    }
    http.open("POST", "CargarPrestamoID", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("id=" + numero);
}

checkUser();