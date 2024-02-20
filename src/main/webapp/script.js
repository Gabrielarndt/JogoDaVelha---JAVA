document.addEventListener('DOMContentLoaded', function() {
    const board = document.getElementById('board');
    const message = document.getElementById('message');
    
    const restartButton = document.getElementById('restartButton');
    
    let currentPlayer = 'X';
    let gameBoard = ['-','-','-','-','-','-','-','-','-'];

    function renderBoard() {
        board.innerHTML = '';
        for (let i = 0; i < 9; i++) {
            const cell = document.createElement('div');
            cell.innerText = gameBoard[i];
            cell.classList.add('cell');
            cell.dataset.index = i;
            cell.addEventListener('click', handleCellClick);
            board.appendChild(cell);
        }
    }

    function handleCellClick(event) {
        const index = event.target.dataset.index;
        if (gameBoard[index] === '-') {
            gameBoard[index] = currentPlayer;
            renderBoard();
            if (checkWinner(currentPlayer)) {
                message.innerText = `Jogador ${currentPlayer} venceu!`;
                removeClickListener();
            } else if (gameBoard.every(cell => cell !== '-')) {
                message.innerText = 'Empate!';
                removeClickListener();
            } else {
                currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            }
        }
    }

    function checkWinner(player) {
        const winConditions = [
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            [0, 4, 8],
            [2, 4, 6]
        ];
        return winConditions.some(condition =>
            condition.every(index => gameBoard[index] === player)
        );
    }

    function removeClickListener() {
        const cells = document.querySelectorAll('.cell');
        cells.forEach(cell => cell.removeEventListener('click', handleCellClick));
    }

    function restartGame() {
        currentPlayer = 'X';
        gameBoard = ['-','-','-','-','-','-','-','-','-'];
        message.innerText = '';
        renderBoard();
    }

    restartButton.addEventListener('click', restartGame);

    renderBoard();
});
