import React from 'react';
import PropTypes from 'prop-types';
import { PacmanLoader } from 'react-spinners';

import './EstablishmentsProfile.css';


export default class EstablishmentsProfile extends React.PureComponent {
  render() {
    return (
      <div id="establishments-profile">
        <h2 id="local-authority-name">{this.props.localAuthorityName} Ratings</h2>
        <div className="center">
          <PacmanLoader
            color="#4bd185"
            loading={this.props.isLoading}
          />
        </div>
        {!this.props.isLoading &&
        <table className="table">
          <thead className="thead-light">
            <tr>
              <th>Rating</th>
              <th>Percentage</th>
            </tr>
          </thead>
          <tbody>
            {this.props.ratingDistribution.map(entry => (
              <tr key={entry.rating}>
                <td>{entry.rating}{isNaN(entry.rating) ? '' : '-star'}</td>
                <td>{entry.percentage}%</td>
              </tr>))}
          </tbody>
        </table>
        }
      </div>
    );
  }
}

EstablishmentsProfile.propTypes = {
  localAuthorityName: PropTypes.string.isRequired,
  ratingDistribution: PropTypes.arrayOf(PropTypes.shape({
    rating: PropTypes.string.isRequired,
    percentage: PropTypes.number.isRequired,
  })).isRequired,
  isLoading: PropTypes.bool.isRequired,
};
