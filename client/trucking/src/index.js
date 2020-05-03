import './index.css';
import * as serviceWorker from './serviceWorker';
import React from 'react';
import ReactDOM from 'react-dom';
import App from "./App";
import {configureFakeBackend} from "./app/_helpers/fake-backend";

configureFakeBackend();

ReactDOM.render(<App/>, document.getElementById('app'));

// ReactDOM.render(<Cargoes/>, document.getElementById('root'));
// ReactDOM.render(<App/>, document.getElementById('app'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
