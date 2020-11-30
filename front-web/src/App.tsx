import React from 'react';
import Alert from './Alert';

const App = () => {
    return (
        <div className="contaiener">
            <Alert text="Tao Hansen" />
            <Alert text="Usuário 1" />
            <Alert text="Usuário 2" />
            <Alert text="Usuário 3" />
        </div>
    );
}

export default App;