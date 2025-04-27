const id = location.href.split("?")[1].split("&").find((row) => row.startsWith("jobid="))?.split("=")[1];
const userid = document.cookie.split("; ").find((row) => row.startsWith("id="))?.split("=")[1];
const editOption = location.href.split("&").find((row) => row.startsWith("edit="))?.split("=")[1];
const fetchedUrl = "/api/job?jobid=" + id;
var datas;

fetch(fetchedUrl, {
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
    dat();
}).catch(error => console.error("Error al leer los datos", error))

function dat(){
    const empleo = document.getElementById("empleo");
    const ubicacion = document.getElementById("ubicacion");
    const fecha = document.getElementById("fecha");
    const jornada = document.getElementById("jornada");
    const categoria = document.getElementById("categoria");
    const desc = document.getElementById("desc");
    const resp = document.getElementById("resp");
    const exp = document.getElementById("exp");
    const form = document.getElementById("registroForm");
    const deleteJob = document.getElementById("delete");

    form.action = "/send/request?jobName=" + datas['name'];
    empleo.value = datas['name'];
    ubicacion.value = datas['location'];
    fecha.value = datas['date'];
    jornada.value = datas['journey'];
    categoria.value = datas['category'];
    desc.value = datas['description'];
    resp.value = datas['responsabilities'];
    exp.value = datas['experiences'];

    if(userid === "1" & editOption === "true"){
        form.action = "/api/edit?jobid="+id;
        empleo.disabled = false;
        ubicacion.disabled = false;
        fecha.disabled = false;
        jornada.disabled = false;
        categoria.disabled = false;
        desc.disabled = false;
        resp.disabled = false;
        exp.disabled = false;
        deleteJob.style.display = "flex";
        deleteJob.disabled = false;
    }
}

function deleteButton(){
    if(userid === "1" & editOption === "true"){
        url = "/api/delete?jobid=" + id;
        window.location = url;
    }
}