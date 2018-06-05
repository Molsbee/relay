'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const restClient = require('./restclient');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {channels: []};
    }

    componentDidMount() {
        restClient({method: 'GET', path: '/channels'}).then(response => {
            this.setState({channels: response.entity});
        });
    }


    render() {
        return (
            <ChannelList channels={this.state.channels}/>
        )
    }

}

class ChannelList extends React.Component {

    render() {
        const channels = this.props.channels.map(channel => {
            return <li>{channel.name}</li>
        });

        return (
            <ul>{channels}</ul>
        )
    }

}

ReactDOM.render(<App />, document.getElementById('react'));