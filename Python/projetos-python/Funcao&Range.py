def Func (n,e):
    p = 1
    for j in range (e):   #Vai até a variavel determinada na linha 1
        p*= n
        return p
    




#-----------------------------------------------------------------
 
    




def Func2 (n):
    f = n
    for i in range (n-1,2,-1): #(start, stop, step)
        f *= n
        return f
   



#------------------------------------------------------------------ 



#Contar Vogais no texto





#---------------------------------------------------------------------


def Func2 (l,a):
    for i in range (len(l)):
        if (l[i] == a):
                return True
    return False


def Func1 (l):
     ld = []
     for i in range (len(l)):
          d = False
          for j in range (len (l)):
               if (i != j and l[i] == l[j]):
                    if (not Func2(ld, l [i])):
                         ld.append(l[i])
                         break
     return ld

lista = ['a','c','b','c','d','c']
print(Func1(lista))
          
        









