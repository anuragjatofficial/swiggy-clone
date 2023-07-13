import { useState } from 'react';
import logo from './images/logo.png';
import SearchIcon from '@material-ui/icons/Search';
import React, { useEffect } from 'react';


let orderData = null;

function NavBar() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        const fetchData = async (num) => {
            try {
                const response = await fetch(`http://localhost:8080/orders/${num}`);
                const jsonData = await response.json();
                setData(jsonData);
                setLoading(false);
                orderData = jsonData;
                console.log(data);
            } catch (error) {
                console.log('Error:', error);
            }
        };



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
                console.log(num);
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

        document.getElementById('searchBtn').addEventListener('click',searchOrder);

        document.getElementById('search-box').addEventListener('keydown',handleInput)

        document.addEventListener('keydown', handleKeyDown);

        return () => {
            document.removeEventListener('keydown', handleKeyDown);
            document.getElementById('search-box').removeEventListener('keydown', handleInput);
            document.getElementById('searchBtn').removeEventListener('click', searchOrder);
        };

    }, [data]);

    return (
        <>
            <div className='mynav'>
                <nav>
                    <div className='logodiv'>
                        <img className='logo' src={logo} />
                    </div>
                    <div className='search'>

                        <input type='Number' id='search-box' placeholder='search with order id' />

                        <div id='searchBtn'>
                            <SearchIcon style={{ fontSize: 32 }} />
                        </div>

                    </div>

                    <ul>
                        <li>
                            <a href='#home' className='myAnchor'>Restaurants</a>
                        </li>
                        <li>
                            <a href='#home' className='myAnchor'>Customers</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </>
    );
}
export { NavBar };

export { orderData };