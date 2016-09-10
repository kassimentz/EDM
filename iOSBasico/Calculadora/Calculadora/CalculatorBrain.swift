//
//  CalculatorBrain.swift
//  Calculadora
//
//  Created by iossenac on 03/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import Foundation

class CalculatorBrain
{
    var accumulator = 0.0
    
    func setOperand(_ operand: Double){
        self.accumulator = operand
    }
    
    enum Operation {
        case constant(Double)
        case unaryOperation((Double)->Double)
        case binaryOperation((Double, Double)->Double)
        case equals
        case clear
    }
    
<<<<<<< HEAD
    let operations : [String:Operation] = [
        "π": Operation.Constant(M_PI),
        "√": Operation.UnaryOperation(sqrt),
        "cos": Operation.UnaryOperation(cos),
        "sen": Operation.UnaryOperation(sin),
        "×": Operation.BinaryOperation({$0 * $1}),
        "÷": Operation.BinaryOperation({$0 / $1}),
        "+": Operation.BinaryOperation({$0 + $1}),
        "-": Operation.BinaryOperation({$0 - $1}),
        "=": Operation.Equals,
        "clr": Operation.Clear
    ]
=======
    
    func selectOperation(_ operation: String) -> Operation{
        switch operation {
        case "π": return Operation.constant(M_PI)
        case "√": return Operation.unaryOperation(sqrt)
        case "cos": return Operation.unaryOperation(cos)
        case "sen": return Operation.unaryOperation(sin)
        case "×": return Operation.binaryOperation({$0 * $1})
        case "÷": return Operation.binaryOperation({$0 / $1})
        case "+": return Operation.binaryOperation({$0 + $1})
        case "-": return Operation.binaryOperation({$0 - $1})
        case "=": return Operation.equals
        case "clr": return Operation.clear
        default: return Operation.equals
        }
    }
>>>>>>> 174ebdd3ad0d81b55e8ccfdb0fe4465f743c1978
    
    struct BinaryOperationInfo {
        var operand: Double
        var operation: (Double, Double)->Double
        
        func exec(_ rhs: Double) -> Double {
            return operation(operand, rhs)
        }
    }
    
    var binaryOperationInfo: BinaryOperationInfo?
    
<<<<<<< HEAD
    func execute(symbol: String){
        if let operation = operations[symbol] {
            switch operation {
            case .Constant(let value): accumulator = value
            case .UnaryOperation(let function): accumulator = function(accumulator)
            case .BinaryOperation(let function):
                binaryOperationInfo = BinaryOperationInfo(operand: accumulator, operation: function)
            case .Equals: if let op = binaryOperationInfo{
=======
    func execute(_ operation: String){
        switch self.selectOperation(operation) {
        case .constant(let value): accumulator = value
        case .unaryOperation(let function): accumulator = function(accumulator)
        case .binaryOperation(let function):
            binaryOperationInfo = BinaryOperationInfo(operand: accumulator, operation: function)
        case .equals: if let op = binaryOperationInfo{
>>>>>>> 174ebdd3ad0d81b55e8ccfdb0fe4465f743c1978
                accumulator = op.exec(accumulator)
                }
            case .Clear:
                accumulator = 0
                binaryOperationInfo = nil
            }
<<<<<<< HEAD
=======
        case .clear:
            accumulator = 0
            binaryOperationInfo = nil
>>>>>>> 174ebdd3ad0d81b55e8ccfdb0fe4465f743c1978
        }
        
    }
}
