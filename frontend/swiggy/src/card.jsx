// this is my 1st jsx file
import { orderData } from "./nav";
function Card() {

    if (orderData == null) {
        return (
            <>
            no
            </>
        );
    }else{
        return (
            <>
                {orderData}
            </>
        );
    }
}

export { Card };