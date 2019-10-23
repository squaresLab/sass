public class Plan1571772158638 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 6 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}



}
}
