var wsUri = "";

function callAjax() {

    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
        console.log('Unable to create XMLHTTP instance');
        return false;
    }
    httpRequest.open('GET', 'AjaxHandler');
    httpRequest.responseType = 'text';
    httpRequest.send();
    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {

            if (httpRequest.status === 200) {

                wsUri = httpRequest.response;

            } else {
                console.log('Something went wrong..!!');
            }
        }
    }
}




function toJSONString() {
    var obj = {};
    var form = document.getElementById("form1");
    var elements = form.querySelectorAll("input, select, textarea");
    for (var i = 0; i < elements.length; ++i) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if (name) {
            obj[name] = value;
        }
    }

    return JSON.stringify(obj);
}

document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("form1");
    var output = document.getElementById("output");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        var json = toJSONString();
        output.innerHTML = json;

    }, false);

});



function init() {
    output = document.getElementById("output");
}

function send_message() {

    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
}

function onOpen(evt) {
    doSend(name.value);
}

function onMessage(evt) {
    buildHtmlTable('#customerDataTable', evt.data);
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function doSend(message) {
    var json = toJSONString(document.getElementById("form1"));
    websocket.send(json);

}

function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;

    output.appendChild(pre);

}

window.addEventListener("load", init, false);

var myList = [];

// Builds the HTML Table out of myList.
function buildHtmlTable(selector, str) {

    var Table = document.getElementById("customerDataTable");
    Table.innerHTML = "";
    var myList = $.parseJSON(str);
    var columns = addAllColumnHeaders(myList, selector);

    for (var i = 0; i < myList.length; i++) {
        var row$ = $('<tr/>');
        for (var colIndex = 0; colIndex < columns.length; colIndex++) {
            var cellValue = myList[i][columns[colIndex]];
            if (cellValue == null) cellValue = "";
            row$.append($('<td/>').html(cellValue));
        }
        $(selector).append(row$);
    }
}

// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records.
function addAllColumnHeaders(myList, selector) {
    var columnSet = [];
    var headerTr$ = $('<tr/>');

    for (var i = 0; i < myList.length; i++) {
        var rowHash = myList[i];
        for (var key in rowHash) {
            if ($.inArray(key, columnSet) == -1) {
                columnSet.push(key);
                headerTr$.append($('<th/>').html(key));
            }
        }
    }
    $(selector).append(headerTr$);

    return columnSet;
}
