// Global state
let display = document.getElementById('display');
let historyDiv = document.getElementById('history');
let statusDiv = document.getElementById('status');

let firstNumber = null;
let operation = null;
let currentInput = '0';
let shouldClearDisplay = false;

const API_BASE_URL = '/api/calculator';

// Format number for display
function formatNumber(num) {
    if (Number.isInteger(num)) {
        return num.toString();
    }
    return num.toFixed(10).replace(/\.?0+$/, '');
}

// Update display
function updateDisplay() {
    display.textContent = currentInput;
}

// Append number to input
function appendNumber(num) {
    if (shouldClearDisplay) {
        currentInput = num;
        shouldClearDisplay = false;
    } else {
        if (currentInput === '0' && num !== '.') {
            currentInput = num;
        } else if (num === '.' && currentInput.includes('.')) {
            return;
        } else {
            currentInput += num;
        }
    }
    updateDisplay();
    setStatus('Ready');
}

// Set operator
function setOperator(op) {
    if (firstNumber === null) {
        firstNumber = parseFloat(currentInput);
    } else if (!shouldClearDisplay) {
        // If user clicks operator again, calculate first
        calculateWithCurrentOperator();
    }
    operation = op;
    shouldClearDisplay = true;
    updateHistory(`${firstNumber} ${op}`);
}

// Set power operator
function setPower() {
    setOperator('^');
}

// Calculate result
async function calculate() {
    if (operation === null || firstNumber === null) {
        return;
    }

    const secondNumber = parseFloat(currentInput);
    
    try {
        setStatus('Calculating...');
        const result = await performCalculation(operation, firstNumber, secondNumber);
        currentInput = formatNumber(result);
        updateDisplay();
        updateHistory(`${firstNumber} ${operation} ${secondNumber} = ${currentInput}`);
        firstNumber = null;
        operation = null;
        shouldClearDisplay = true;
        setStatus('Calculated');
    } catch (error) {
        setError(error.message);
    }
}

// Calculate with current operator (for chaining operations)
async function calculateWithCurrentOperator() {
    if (operation === null || firstNumber === null) {
        return firstNumber;
    }

    const secondNumber = parseFloat(currentInput);
    
    try {
        const result = await performCalculation(operation, firstNumber, secondNumber);
        firstNumber = result;
        currentInput = formatNumber(result);
        updateDisplay();
        operation = null;
        return result;
    } catch (error) {
        setError(error.message);
        return firstNumber;
    }
}

// Perform calculation via API
async function performCalculation(op, a, b) {
    let endpoint = '';
    let params = `a=${a}&b=${b}`;

    switch (op) {
        case '+':
            endpoint = 'add';
            break;
        case '-':
            endpoint = 'subtract';
            break;
        case '*':
            endpoint = 'multiply';
            break;
        case '/':
            endpoint = 'divide';
            break;
        case '^':
            endpoint = 'power';
            params = `base=${a}&exponent=${b}`;
            break;
        default:
            throw new Error('Invalid operation');
    }

    const response = await fetch(`${API_BASE_URL}/${endpoint}?${params}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.error || 'Calculation error');
    }

    const data = await response.json();
    return data.result;
}

// Perform square root
async function performSqrt() {
    const number = parseFloat(currentInput);
    
    try {
        setStatus('Calculating...');
        const response = await fetch(`${API_BASE_URL}/sqrt?a=${number}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.error || 'Calculation error');
        }

        const data = await response.json();
        currentInput = formatNumber(data.result);
        updateDisplay();
        updateHistory(`√${number} = ${currentInput}`);
        firstNumber = null;
        operation = null;
        shouldClearDisplay = true;
        setStatus('Calculated');
    } catch (error) {
        setError(error.message);
    }
}

// Clear display
function clearDisplay() {
    currentInput = '0';
    firstNumber = null;
    operation = null;
    shouldClearDisplay = false;
    updateDisplay();
    updateHistory('');
    setStatus('Cleared');
}

// Delete last character
function deleteLastChar() {
    if (currentInput.length > 1) {
        currentInput = currentInput.slice(0, -1);
    } else {
        currentInput = '0';
    }
    updateDisplay();
}

// Update history
function updateHistory(text) {
    historyDiv.textContent = text;
}

// Set status message
function setStatus(message) {
    statusDiv.textContent = message;
    statusDiv.classList.remove('error');
}

// Set error message
function setError(message) {
    statusDiv.textContent = `Error: ${message}`;
    statusDiv.classList.add('error');
}

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    updateDisplay();
    setStatus('Ready');
    
    // Check API health
    fetch(`${API_BASE_URL}/health`)
        .then(response => response.json())
        .then(data => {
            console.log('API Status:', data);
        })
        .catch(error => {
            console.error('API Error:', error);
            setError('Cannot connect to API');
        });
});

// Keyboard support
document.addEventListener('keydown', (e) => {
    if (e.key >= '0' && e.key <= '9') appendNumber(e.key);
    if (e.key === '.') appendNumber('.');
    if (e.key === '+') setOperator('+');
    if (e.key === '-') setOperator('-');
    if (e.key === '*') setOperator('*');
    if (e.key === '/') {
        e.preventDefault();
        setOperator('/');
    }
    if (e.key === 'Enter' || e.key === '=') {
        e.preventDefault();
        calculate();
    }
    if (e.key === 'Backspace') {
        e.preventDefault();
        deleteLastChar();
    }
    if (e.key === 'Escape') {
        clearDisplay();
    }
});
