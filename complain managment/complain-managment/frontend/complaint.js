async function submitComplaint(){

    const userId =
    localStorage.getItem(
    "userId");

    const data={

        title:
        document.getElementById(
        "title").value,

        description:
        document.getElementById(
        "description").value,

        category:"General",

        status:"Pending",

        user:{
            id:userId
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
        JSON.stringify(data)

    });

    if(response.ok){

        alert(
        "Complaint Submitted");

    }

    else{

        alert(
        "Error");

    }

    }

    catch(error){

        console.log(error);

    }

}