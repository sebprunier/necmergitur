var cheerio = require('cheerio');
var request = require('request');

var options = {
  url: 'http://www.aphp.fr/hopitaux',
  headers: {
    'User-Agent': 'request'
  }
};

request(options, (err, response, body) => {
    if (err) throw err;

    var $ = cheerio.load(body);

    $('div.hospital-info').map((i, hospital) => {
        var hospitalNode = $(hospital);
        var hospitalName = $('span.hospital-name > a', hospitalNode).text();
        var hospitalCoordsLink = $('a.map-link', hospitalNode).attr('href');
        var hospitalCoords = hospitalCoordsLink.split('@')[1].split(',');

        console.log(`${hospitalName};${hospitalCoords[0]};${hospitalCoords[1]}`);
        // console.log(`{"name": "${hospitalName}", "location": [${hospitalCoords[0]},${hospitalCoords[1]}]},`);
    });
});
