import {useState} from "react";
import {router} from "next/client";


export default function Waiting(){
    const [host, setHost] = useState(true);
    const [isChecked, setIsChecked] = useState(false);
    const [numPlayer, setNum] = useState(0)
    const handleCheckboxChange = () => {
        if (numPlayer < 2) {
            setIsChecked(true)
        } else {
            setIsChecked(false)
        }
    };
    return (

        <div>
            <div className="numPlay">
                {numPlayer}
            </div>
            <div className="wait">
                {host ? <button className="wait-btn" disabled={isChecked}  onClick={() => router.push("/game")}>Start</button> : false}
            </div>
        </div>
    );
}