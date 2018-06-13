'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const restClient = require('./restclient');
const { BrowserRouter, Switch, Route, Link } = require('react-router-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userDetails: {},
        };
    }

    componentDidMount() {
        restClient({method: 'GET', path: '/users?self=true'}).then(response => {
            this.setState({
                userDetails: response.entity[0],
            });
        });
    }

    render() {
        return (
            <div className={"container-fluid h-100"}>
                <nav className={"navbar navbar-dark fixed-top bg-dark flex-md-nowrap"}>
                    <a className={"navbar-brand"} href={"#"}>
                        Relay<br/>
                        <span style={{fontSize: "14px"}}>{this.state.userDetails.firstName} {this.state.userDetails.lastName}</span>
                    </a>
                </nav>
                <main>
                    <Switch>
                        <Route path={'/'} render={() => <Home userDetails={this.state.userDetails}/>}/>
                    </Switch>
                </main>
            </div>
        )
    }

}

class Home extends React.Component {

    constructor(props) {
        super(props);
        const channels = this.props.userDetails.subscribedChannels ? this.props.userDetails.subscribedChannels : [];
        this.state = {
            channels: channels,
            channel: ""
        }
    }

    render() {
        console.log("Home: " + JSON.stringify(this.props.userDetails));

        const channels = this.state.channels.map(channel => {
            return <a className={"nav-link"} onClick={this.handleClick.bind(this, channel.name)}>{channel.name}</a>
        });

        return (
            <div className={"row h-100"}>
                <div className={"col-md-3"} style={{backgroundColor: "#2b6f7a", color: "#f2f2f2", paddingTop: "90px"}}>
                    <div id={"channels"}>
                        <nav className={"nav flex-column"}>Channels</nav>
                        <nav className={"nav flex-column"}>{channels}</nav>
                    </div>
                </div>
                <div className={"col-md-9"} style={{paddingTop: "90px"}}>
                    <div style={{height: "95%"}}>
                        {/*<Channel channel={this.state.channel}/>*/}
                    </div>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="inputGroup-sizing-default">Default</span>
                        </div>
                        <input type="text" className="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"/>
                    </div>
                </div>
            </div>
        )
    }
}

ReactDOM.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>, document.getElementById('react'));