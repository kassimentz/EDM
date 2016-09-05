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
    
    struct BinaryOperationInfo {
        var operand: Double
        var operation: (Double, Double)->Double
        
        func exec(_ rhs: Double) -> Double {
            return operation(operand, rhs)
        }
    }
    
    var binaryOperationInfo: BinaryOperationInfo?
    
    func execute(_ operation: String){
        switch self.selectOperation(operation) {
        case .constant(let value): accumulator = value
        case .unaryOperation(let function): accumulator = function(accumulator)
        case .binaryOperation(let function):
            binaryOperationInfo = BinaryOperationInfo(operand: accumulator, operation: function)
        case .equals: if let op = binaryOperationInfo{
                accumulator = op.exec(accumulator)
            }
        case .clear:
            accumulator = 0
            binaryOperationInfo = nil
        }
    }
}
