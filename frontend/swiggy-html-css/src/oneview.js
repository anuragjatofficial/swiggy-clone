const baseURL = "http://localhost:8080";
//base URL


const handleKeyDown = (event) => {
    if (event.ctrlKey && event.code === 'Space') {
        event.preventDefault();
        document.getElementById('search-box').focus();
    }
};

const handleInput = (e) => {
    if (e.key === 'Enter') {
        let num = document.getElementById('search-box').value;
        fetchData(num);
    }
}

const searchOrder = () => {

    const num = document.getElementById("search-box").value;

    if (num < 0) {
        console.log("number is less than 0");
    } else {
        fetchData(num);
    }

};

document.getElementById('searchBtn').addEventListener('click', searchOrder);

document.getElementById('search-box').addEventListener('keydown', handleInput)

document.addEventListener('keydown', handleKeyDown);

function fetchData(num){
    fetch(`${baseURL}/orders/${num}`)
    .then(res=>res.json())
    .then(res=>{
        console.log(res);
        displayOrderData(res);
    })
    .catch((err)=>{
        console.log(err);
    })
}

function displayOrderData(res){
    return ``;
}