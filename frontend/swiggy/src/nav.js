import { useState } from 'react';

// count button using react

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
                    <ul>
                        <li>
                            <a href='#home' className='myAnchor'>Home</a>
                        </li>
                        <li>
                            <a href='#home' className='myAnchor'>Contact</a>
                        </li>
                        <li>
                            <a href='#home' className='myAnchor'>About</a>
                        </li>
                        <li>
                            <a href='#home' className='myAnchor'>Help</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </>
    );
}
export { MyButton };
export { NavBar };