s = str(raw_input('insert old time pattern:'))
sArr = s.split(',')
iArr = ''
for s in sArr:
	data = int(s)
	ms = data % 100
	data /= 100
	s = data % 100
	data /= 100
	m = data
	iArr += str(ms + s*30 + m*30*60) + ','
print iArr
