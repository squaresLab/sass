public class Plan1571771859926 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


} else {
StartServer("B");
}

}

}
}
