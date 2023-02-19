let program = `PROGRAM Sample (Input, Output);
  CONST
    x  = 15;
    
    BEGIN
    END;
`;


const WHITESPACE = /\s/;
const ALPHANUMERIC = /[A-Za-z0-9]/;
const NUMERIC = /[0-9]/;

const IGNORE_TOKEN = {};

/**
 * 
 * @param {string} program 
 */
function lexer(program) {

    const tokens = [];
    let token = IGNORE_TOKEN;

    for (let i = 0; i < program.length; i++) {
        if (WHITESPACE.test(program[i])) {
            token = IGNORE_TOKEN;
        }
        else if (ALPHANUMERIC.test(program[i])) {
            if (token.type !== 'tokword' && token.type !== 'toknumber') {
                token = { source: '', type: NUMERIC.test(program[i]) ? 'toknumber' : 'tokword' };
                tokens.push(token);
                //console.log(tokens)
            }
            token.source += program[i];
            //console.log(tokens)
        }
        else {
            if (token.type !== 'tokop' || token.source.length > 0) {
                token = { source: program[i], type: 'tokop' };
                tokens.push(token);
            }
        }
    }

    console.table(tokens)
    return tokens
}

lexer(program);
