def sortString(a):
    return ''.join(sorted(a))

def foo(st):
    substrdic = {} # keeps count of the substrings in the string
    sizeList = range(1, (len(st)+1) )
    cnt = 0
    sstr = ""
    for index, char in enumerate(st): # for each character
        for size in sizeList: # for each substring starting with this letter
            if ( (index+size) <= len(st) ): # is it a valid size?
                #sort and store in dictionary, if found once again then count already spotted as anagram pairs
                sstr = sortString( st[index:(index+size)] )
                if sstr in substrdic:
                    cnt += substrdic[sstr]
                    substrdic[sstr] += 1 # 1 plus
                else:
                    substrdic[sstr] = 1 # 1 spotted count

    print cnt
    
N = int(raw_input())
for i in xrange(N):
    foo(raw_input())
