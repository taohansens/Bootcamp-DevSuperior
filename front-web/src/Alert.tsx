import React from 'react';

type Props = {
    text?: string; // Com "?"" -> Opcional
}

const Alert = ({ text }: Props) => (
    <div className="alert alert-primary">
        Bem vindo, { text }.
    </div>
);

export default Alert;