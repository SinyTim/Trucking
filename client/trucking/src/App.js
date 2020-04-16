import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  state = {};

  componentDidMount() {
    this.hello()
  }

  hello = () => {
    fetch('http://localhost:8080/api')
      .then(response => response.text())
      .then(message => {
        this.setState({message: message});
      });
  };

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h3 className="App-title">{this.state.message}</h3>
        </header>
        <p className="App-intro"></p>
      </div>
    );
  }
}

export default App;
