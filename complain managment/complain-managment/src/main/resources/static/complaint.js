window.onload = function () {

    const userId =
    localStorage.getItem(
    "userId"
    );

    if (!userId) {

        alert(
        "Please login first"
        );

        window.location.href =
        "login.html";

        return;

    }

    const username =
    localStorage.getItem(
    "userName"
    );

    const nameBox =
    document.getElementById(
    "username"
    );

    if(nameBox){

        nameBox.innerText =
        username;

    }

};


async function submitComplaint() {

    const userId =
    localStorage.getItem(
    "userId"
    );

    if(!userId){

        alert(
        "Please login first"
        );

        window.location.href =
        "login.html";

        return;

    }

    const title =
    document
    .getElementById(
    "title"
    )
    .value
    .trim();

    const description =
    document
    .getElementById(
    "description"
    )
    .value
    .trim();

    if(
    !title ||
    !description
    ){

        alert(
        "Please fill all fields"
        );

        return;

    }

    const formData =
    new FormData();

    formData.append(
    "title",
    title
    );

    formData.append(
    "description",
    description
    );

    const fileInput =
    document
    .getElementById(
    "image"
    );

    if(
    fileInput.files.length > 0
    ){

        formData.append(

        "file",

        fileInput.files[0]

        );

    }

    formData.append(
    "userId",
    userId
    );

    try {

        const response =
        await fetch(

        `${window.location.origin}/api/complaints/upload`,

        {

        method:"POST",

        body:formData

        }

        );

        if(response.ok){

            alert(
            "Complaint Submitted Successfully"
            );

            location.reload();

        }

        else{

            const error =
            await response.text();

            console.log(error);

            alert(
            "Upload failed"
            );

        }

    }

    catch(error){

        console.log(error);

        alert(
        "Error: "
        + error.message
        );

    }

}



function logout(){

localStorage.clear();

window.location.href=
"login.html";

}