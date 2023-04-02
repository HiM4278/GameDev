import React from 'react';

function ErrorBox(props : any) {
    return (
        <div className="error-box">
            <p className="error-message">{props.errorMessage}</p>
        </div>
    );
}

export default ErrorBox;
