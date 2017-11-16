!!! Assembly coding project to translate character codes...
!!! ...from standard input to standard output & prints a summary analysis	
!!! Maria E Kloiber 12/11

	_EXIT   = 1
	_READ   = 3
	_WRITE  = 4
	_STDIN  = 0
	_STDOUT = 1
	_GETCHAR = 117

	MAXBUFF = 80
.SECT .TEXT
start:	! print prompt, as detailed in next five instructions
	PUSH    buff-prompt	! third arg is length of prompt
	PUSH    prompt          ! second arg is address of prompt
	PUSH    _STDOUT         ! first arg is standard output
	PUSH    _WRITE          ! name of the OS function being called
	SYS                     ! perform the system call
	ADD     SP,8            ! clean up stack
	
	PUSH	MAXBUFF		! read two characters...
	PUSH    buff            ! into buff...
	CALL	getline
	ADD     SP,4            ! clean up stack
	
	PUSH    AX
	PUSH	buff
	PUSH    _STDOUT         ! on standard output
	PUSH    _WRITE
	SYS
        ADD     SP,8            ! clean up stack
	
        PUSH    0               ! exit with normal exit status
	PUSH    _EXIT
	SYS

getline:
	PUSH	BP
	MOV	BP,SP
	PUSH	BX

	MOV     BX,4(BP)	! This is buff - the address of what we're writing into
	MOV	AX,6(BP)	! This is the size of that buffer
	SUB	AX,1
	
1:	CMP	BX,AX		!if arg2-1 characters have already been stored in arg1
	JE	9f
	PUSH	_GETCHAR
	SYS
	ADD	SP,2
	CMP	AX,-1
	JE	9f
	MOVB	(BX),AL
	INC	BX
	CMPB	AL,'\n'
	JE	9f
	JMP	1b
	
9:  	MOV     (BX),0          ! to end of input
	SUB	BX,buff
	MOV	AX,BX		! AX and BX have the number of chars read 
	
	POP	BX
	POP	BP
	RET

.SECT .DATA
prompt:
.ASCII  "Type a line of input, then press ENTER:  "
buff:	
	.SPACE  2

	
.SECT .BSS
