const welcome = document.getElementById("welcome");
const username = document.cookie.split("; ").find((row) => row.startsWith("name="))?.split("=")[1];
if(username){
    console.log("Bienvenid@ " + username);
    welcome.innerText += " " + username.toUpperCase();
}

function adminBtn(){
    window.location.href = "/api/check"
}

let datas
let space = document.getElementById("DOMElements");

fetch("http://localhost:8070/api/offers", {
    method: 'get',
    headers: {
        'Content-Type': 'application/json',
    },
})
.then(Response => {
    const content = Response.headers.get('Content-Type');
    console.log(content);
    if(content && content.includes('application/json')){
        return Response.json();
    }else{
        throw new Error('The resonse isn\'t json');
    }
})
.then(data => {
    datas = data;
    console.log(data)
    console.log(data[0])
    console.log(data[0]['job'])
    loop();
}).catch(error => console.error("Error al leer los datos", error))

function loop(){
    for(i = 0; i < datas.length - 2; i++){
        charge(i);
    }
}

function charge(i){
    let format = `<section class="joboffer">
    <h1 class="jobTitle">${datas[i]['job']}</h1>
    <h3 class="jobSub">Ciudad: ${datas[i]['location']}</h3>
    <h3 class="jobSub">Jornada: ${datas[i]['journey']}</h3>
    <p class="jobInfo">Descripcion: ${datas[i]['description']}</p>
    <button class="jobBtn" onclick="chargejob(${datas[i]['offerid']})">Aplicar</button>
    </section>`
    space.innerHTML += format;
}

/*function chargejob(id){
    fetch("/api/job?jobid=1")
    .then(Response => {
        const content = Response.headers.get('Content-Type');
        console.log(content);
        if(content && content.includes('application/json')){
            return Response.json();
        }else{
            throw new Error('The resonse isn\'t json');
        }
    })
    .then(data => {
        datas = data;
        console.log(data)
    }).catch(error => console.error("Error al leer los datos", error))
}*/

function chargejob(id){
    if(id == 1){
        window.location = "/job?jobid=" + id + "&edit=true";
    }else{
        window.location = "/job?jobid=" + id;
    }
}

function logout(){
    window.location.reload();
    document.cookie = "name=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}