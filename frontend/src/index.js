import React from 'react';
import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import ReactDOM from 'react-dom';

import configureStore from './configureStore';
import Main from './components/MainContainer';

const store = configureStore();

ReactDOM.render(
  <Provider store={store}>
    <Router>
      <Route path="/" component={Main} />
    </Router>
  </Provider>,
  document.getElementById('root'),
);
