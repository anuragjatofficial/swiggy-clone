import { useState } from 'react';
import logo from './images/logo.png';
import SearchIcon from '@material-ui/icons/Search';

function MyButton() {
    const [count, setcount] = useState(0);
    function handleClick() {
        setcount(count + 1);
    }

    return (<button onClick={handleClick} style={{ cursor: "pointer" ,userSelect:"none"}}>I am a button click me you clicked me {count} times </button>);
}
// navbar using react
function NavBar() {
    return (
        <>
            <div className='mynav'>
                <nav>
                    <div className='logodiv'>
                        <img className='logo' src={logo}/>
                    </div>
                    <div className='search'>
                        <input type='text' placeholder='search with order id'/>
                        <div>
                            <SearchIcon style={{ fontSize: 32 }} />
                        </div>
                    </div>
                    {/* <div>
                    </div> */}
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
export { MyButton };
export { NavBar };