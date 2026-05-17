async function submitComplaint(){

const formData = new FormData();

formData.append(
"title",

document
.getElementById(
"title"
).value
);

formData.append(
"description",

document
.getElementById(
"description"
).value
);

const fileInput =
document
.getElementById(
"image"
);

if(
fileInput.files.length>0
){

formData.append(

"file",

fileInput
.files[0]

);

}

formData.append(

"userId",

localStorage
.getItem(
"userId")

);

try{

const response=

await fetch(

"http://localhost:8080/api/complaints/upload",

{

method:"POST",

body:
formData

}

);

if(response.ok){

alert(
"Complaint Submitted Successfully"
);

window.location.reload();

}else{

alert(
"Upload failed"
);

}

}catch(error){

console.log(error);

alert(
"Error"
);

}

}