# Code review

## Things I look for:

- Is the styling consistent?
	- (Especially when adding to existing projects)
- Are classes, methods and variables clearly named?
- Are there any 'magic numbers' or 'magic strings'?
- Is there any documentation?
- Is every class responsible for one thing only? (Single Responsibility)
- When using inheritance, is polymorphism applied correctly, if relevant?
	- (Sometimes the reuse is more important than the polymorphism)
- How long are the methods?
	- If longer than 10 to 15 lines, could it be shortened?
- Is there a lot of nesting of if-statements and/or loops?
- Is there any duplication?
- Are there any tests?
	- Do they properly verify functionality?
	- Are edge cases tested?