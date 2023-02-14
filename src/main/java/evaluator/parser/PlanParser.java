package evaluator.parser;

import evaluator.node.Expression;
import evaluator.node.Plan;
import evaluator.parser.Parser;
import exeption.SyntaxErrorException;

public class PlanParser implements Parser {

    @Override
    public Plan parse() throws SyntaxErrorException {
        return null;
    }

    private Expression parseExpression(){
//        Expr expr = parseT();
//        try {
//            while(tkz.peek("+") || tkz.peek("-")) {
//                String operator = tkz.consume();
//                if(operator.equals("+")) {
//                    expr = new BinaryArithExpr(expr,"+",parseT());
//                } else if(operator.equals("-")){
//                    expr = new BinaryArithExpr(expr,"-",parseT());
//                }
//            }
//        } catch (NoSuchElementException | UnknownTokenException e){
//            throw new exeption.SyntaxErrorException(e.getMessage());
//        }
//        return expr;
        return null;
    }
}
