async function submitComplaint(){

const file =
document
.getElementById(
"image")
.files[0];

let imageName="";

if(file){

imageName=
file.name;

}

const data={

title:
document
.getElementById(
"title")
.value,

description:
document
.getElementById(
"description")
.value,

category:
"General",

status:
"Pending",

imageName:
imageName,

user:{
id:
localStorage
.getItem(
"userId")
}

};

try{

const response=
await fetch(
"http://localhost:8080/api/complaints",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data)

});

if(response.ok){

alert(
"Complaint Submitted");

}

else{

alert(
"Error");

}

}catch(error){

console.log(
error);

}

}