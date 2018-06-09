'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const ReactBootstrap = require('react-bootstrap');
const restClient = require('./restclient');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {userDetails: {}, channels: []};
    }

    componentDidMount() {
        restClient({method: 'GET', path: '/users?self=true'}).then(response => {
            const userDetails = response.entity[0];
            const channels = userDetails.subscribedChannels ? userDetails.subscribedChannels : [];
            console.log("Channels: " + channels);
            this.setState({
                userDetails: userDetails,
                channels: channels
            });
        });
    }

    render() {
        return (
            <div>
                <nav className={"navbar navbar-expand navbar-dark bg-dark"}>
                    <a className={"navbar-brand"} href={"#"}>
                        Relay<br/>
                        <span style={{fontSize: "14px"}}>{this.state.userDetails.firstName} {this.state.userDetails.lastName}</span>
                    </a>
                </nav>
                <div className={"container-fluid"}>
                    <div className={"row"}>
                        <div className={"col-md-2"} style={{backgroundColor: "#006680", color: "#f2f2f2"}}>
                            <div className={"row"}>
                                {/*User channels*/}
                                <ChannelList channels={this.state.channels}/>
                            </div>
                        </div>
                        <div className="col-md-10">
                            {/*Message Page*/}
                        </div>
                    </div>
                </div>
            </div>
        )
    }

}

class ChannelList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {channel: ""};
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(channelName) {
        console.log(channelName);
    }

    render() {
        console.log(this.props.channels);
        const channels = this.props.channels.map(channel => {
            return <a className={"nav-link"} onClick={this.handleClick.bind(this, channel.name)}>{channel.name}</a>
        });

        return (
            <div id="channels">
                <nav className={"nav flex-column"}>{channels}</nav>
            </div>
        )
    }

}

ReactDOM.render(<App />, document.getElementById('react'));