import React, {useState, useEffect} from 'react';


function App() {
    const [counter, setCounter] = useState(0);

    useEffect(()=>{
        console.log('o contador mudou de valor')
    }, [counter]) //segundo valor dependência, useEffect.

    return (
        <div className="container mt-5">
            <button
                className="btn btn-primary mr-5"
                onClick={() => setCounter(counter+1)}>
            +
            </button>
            <span>
                {counter}
            </span>
            <button
                className="btn btn-primary ml-5"
                onClick={() => setCounter(counter-1)}>
                -
            </button>
        
            {counter < 5 &&
                <p className="alert alert-danger mt-3">
                O valor é menor que 5 </p>}
            {counter > 5 &&
            <p className="alert alert-success mt-3">
            O valor é maior que 5 </p>}
        </div>
    )
}

export default App;