public class Plan1571768529971 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}



for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


} else {
StartServer("B");
}

}

}
}
