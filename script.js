const apiBaseUrl = "http://localhost:5500/api/calculator";
let display = document.getElementById("display");
let operator = "";
let firstOperand = null;

function appendToDisplay(value) {
    display.value += value;
}

function clearDisplay() {
    display.value = "";
    firstOperand = null;
    operator = "";
}

function setOperator(op) {
    firstOperand = parseFloat(display.value);
    operator = op;
    display.value = "";
}

function calculate() {
    const secondOperand = parseFloat(display.value);
    const encodedOperator = encodeURIComponent(operator);

    fetch(`${apiBaseUrl}/calculate?num1=${firstOperand}&num2=${secondOperand}&operator=${encodedOperator}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch calculation");
            }
            return response.json();
        })
        .then(result => display.value = result)
        .catch(error => display.value = "Error: " + error.message);
}

function calculateSqrt() {
    const value = parseFloat(display.value);

    fetch(`${apiBaseUrl}/sqrt?num=${value}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch square root");
            }
            return response.json();
        })
        .then(result => display.value = result)
        .catch(error => display.value = "Error: " + error.message);
}

function calculateTrig(func) {
    const angle = parseFloat(display.value);

    fetch(`${apiBaseUrl}/trig?func=${func}&angle=${angle}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch trigonometric calculation");
            }
            return response.json();
        })
        .then(result => display.value = result)
        .catch(error => display.value = "Error: " + error.message);
}
